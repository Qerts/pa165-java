package cz.fi.muni.pa165.service;

import cz.fi.muni.pa165.dao.interfaces.Dao;
import cz.fi.muni.pa165.dao.interfaces.EmployeeDao;
import cz.fi.muni.pa165.entity.Employee;
import cz.fi.muni.pa165.service.interfaces.EmployeeService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

/**
 * @author Jozef Krcho
 */
@Service
public class EmployeeServiceImpl extends JpaService<Employee, Long> implements EmployeeService {

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int SALT_BYTE_SIZE = 24;
    private static final int HASH_BYTE_SIZE = 18;
    private static final int PBKDF2_ITERATIONS = 64000;

    @Inject
    private EmployeeDao employeeDao;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected Dao<Employee, Long> getDao() {
        return employeeDao;
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public void registerEmployee(Employee employee, String unencryptedPassword) throws IllegalArgumentException {
        if (employee == null) throw new IllegalArgumentException("employee parameter is null");
        if (unencryptedPassword == null) throw new IllegalArgumentException("unencryptedPassword parameter is null");
        if (unencryptedPassword.isEmpty()) throw new IllegalArgumentException("unencryptedPassword is empty");
        //employee.setPasswordHash(createHash(unencryptedPassword));
        employee.setPasswordHash(passwordEncoder.encode(unencryptedPassword));
        this.create(employee);
    }

    @Override
    public boolean authenticate(Employee employee, String password) {
        if (employee == null) return false;
        if (password == null) return false;
        //return verifyPassword(password, employee.getPasswordHash());
        return passwordEncoder.matches(password, employee.getPasswordHash());
    }

    /**
     * source: https://crackstation.net/hashing-security.htm#javasourcecode
     * Create encrypted password in format iterations:salt:hash
     *
     * @param password unencrypted password
     * @return encrypted password
     */
    private static String createHash(String password) {
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

        // format: iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toBase64(salt) + ":" + toBase64(hash);
    }

    private static boolean verifyPassword(String password, String correctHash) {
        if (password == null) return false;
        if (correctHash == null) return false;

        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromBase64(params[1]);
        byte[] hash = fromBase64(params[2]);

        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    /**
     * PBKDF2 applies a pseudorandom function, such as a cryptographic hash, cipher, or hash-based message
     * authentication code (HMAC), to the input password or passphrase along with a salt value
     * and repeats the process many times to produce a derived key, which can then be used
     * as a cryptographic key in subsequent operations. The added computational work makes password
     * cracking much more difficult, and is known as key stretching.
     *
     * @param password   input unencryptedPassword
     * @param salt       salt for password
     * @param iterations iteration count for generating PBEKey
     * @param bytes      hash lenght
     * @return hash
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance(PBKDF2_ALGORITHM).generateSecret(spec).getEncoded();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Converts the string argument into an array of bytes
     *
     * @param hex A string containing lexical representation of xsd:base64Binary
     * @return array of bytes represented by the string argument
     * @throws IllegalArgumentException
     */
    private static byte[] fromBase64(String hex) throws IllegalArgumentException {
        return DatatypeConverter.parseBase64Binary(hex);
    }

    /**
     * Converts an array of bytes into a string.
     *
     * @param array An array of bytes
     * @return A string containing a lexical representation of xsd:base64Binary
     * @throws IllegalArgumentException
     */
    private static String toBase64(byte[] array) throws IllegalArgumentException {
        return DatatypeConverter.printBase64Binary(array);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }
}
