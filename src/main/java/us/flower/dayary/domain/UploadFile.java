package us.flower.dayary.domain;

import lombok.Data;
import us.flower.dayary.domain.common.DateAudit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UploadFile extends DateAudit {

    @Id
    @GeneratedValue
    long id;

    @Column
    String fileName;

    @Column
    String saveFileName;

    @Column
    String filePath;

    @Column
    String contentType;

    @Column
    long size;
}
