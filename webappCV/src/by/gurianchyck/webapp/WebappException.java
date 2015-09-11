package by.gurianchyck.webapp;

import by.gurianchyck.webapp.model.Resume;

/**
 * Created by Alexey Gurianchyck
 * 11.09.2015.
 */
public class WebappException extends RuntimeException {
    private Resume resume = null;
    private String uuid = null;

    public WebappException(String message) {
        super(message);
    }

    public WebappException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebappException(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public WebappException(String message, Throwable cause, Resume resume) {
        super(message, cause);
        this.resume = resume;
    }

    public WebappException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public Resume getResume() {
        return resume;
    }

    public String getUuid() {
        return uuid;
    }
}
