package io.lavoisier.db.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * The result of a Reaction execution
 */
@Entity
@Table(name = "lav_reaction_log")
public class ReactionLog {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "log_id")
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name = "log_execution_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date executionDate;

    @Column(name = "log_success", nullable = false)
    private boolean success;

    @Column(name = "log_error_message")
    private String errorMessage;

    public UUID getId() {
        return id;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
