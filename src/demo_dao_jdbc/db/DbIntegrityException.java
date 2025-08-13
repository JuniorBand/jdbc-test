package demo_dao_jdbc.db;

public class DbIntegrityException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Integrity constraint violation";

    public DbIntegrityException(String message) {
        super( DEFAULT_MESSAGE + "\n" + message);
    }
}
