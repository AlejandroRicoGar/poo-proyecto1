package upm.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public enum Categories {
    SCORED_POINTS,
    MATCH_WON,
    ASISTS_POINTS,
    PAST_TOURNAMENTS,
    GENERATED_MONEY
}
