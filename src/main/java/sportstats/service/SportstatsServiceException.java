package sportstats.service;

/**
 *
 * @author thomas
 */
public class SportstatsServiceException extends RuntimeException {

    public SportstatsServiceException(String message) {
        super(message);
    }

    public SportstatsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SportstatsServiceException(Throwable cause) {
        super(cause);
    }
}
