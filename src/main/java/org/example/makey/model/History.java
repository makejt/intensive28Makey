package org.example.makey.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Embeddable
@Data
@NoArgsConstructor
public class History {
    @Column(name = "created")
    private Timestamp created = new Timestamp(System.currentTimeMillis());
    @Column(name = "last_updated")
    private Timestamp lastUpdated;

}
