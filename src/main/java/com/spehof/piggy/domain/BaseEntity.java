package com.spehof.piggy.domain;

import javax.persistence.MappedSuperclass;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@MappedSuperclass
public class BaseEntity {

    protected boolean sameAsFormer(BaseEntity oldBaseEntity ,BaseEntity newBaseEntity) {
        return oldBaseEntity == null ?
                newBaseEntity == null : oldBaseEntity.equals(newBaseEntity);
    }
}
