package by.tc.like_it.dao.pool.exception;

/**  The ConnectionPoolException Exception provides information about violation
 *  in the work of the connection pool. {@link by.tc.like_it.dao.pool.ConnectionPool}
 *  @author AnastasiyaHelda
 */
public class ConnectionPoolException extends Exception{
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Exception e) {
        super(e);
    }

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
