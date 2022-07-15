package com.angelo.cleanarch.business.domain.utils

/**
 * Interface EntityMapper
 * Utility to map to and from Entity & DomainModel
 */
interface EntityMapper <Entity, DomainModel>{

    /**
     * Map from entity to domain model
     */
    fun mapFromEntity(entity: Entity): DomainModel

    /**
     * Map from domain to entity model
     */
    fun mapToEntity(domainModel: DomainModel): Entity

    /**
     * Map from entity list to domain model list
     */
    fun mapFromEntityList(entityList: List<Entity>): List<DomainModel>

    /**
     * Map from domain list to entity model list
     */
    fun mapToEntityList(domainModelList: List<DomainModel>): List<Entity>
}