package com.dariawan.notesapp.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

@Validated
@Entity
@Table(name = "notes")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Note implements Serializable {

    private static final long serialVersionUID = 5057388942388599423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Column(length = 4000)
    private String content;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdOn;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedOn;
    
    public String getShortContent() {
        return StringUtils.left(this.content, 200);
    }
}
