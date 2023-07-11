package org.example.makey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Embeddable
@Data
@NoArgsConstructor
public class History {
    @Column(name = "created")
    private Timestamp created;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;

}
