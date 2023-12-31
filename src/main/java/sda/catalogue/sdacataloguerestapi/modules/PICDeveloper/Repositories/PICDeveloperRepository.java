package sda.catalogue.sdacataloguerestapi.modules.PICDeveloper.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sda.catalogue.sdacataloguerestapi.modules.PICDeveloper.Entities.PICDeveloperEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface PICDeveloperRepository extends JpaRepository<PICDeveloperEntity, Long> {

    //Getting data PIC Developer with search and pagination
    @Query("SELECT w FROM PICDeveloperEntity w " +
            "WHERE LOWER(w.personalNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "   OR LOWER(w.personalName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "ORDER BY w.updatedAt DESC")
    List<PICDeveloperEntity> findBySearchTerm(String searchTerm, Pageable pageable);

    //Counting data PIC Developer with search
    @Query("SELECT COUNT(w) FROM PICDeveloperEntity w " +
            "WHERE " +
            "LOWER(w.personalNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(w.personalName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ")
    long countBySearchTerm(String searchTerm);

    //Getting data PIC Developer by UUID
    PICDeveloperEntity findByUuid(UUID uuid);

    //Updating data PIC Developer by UUID
    @Modifying
    @Transactional
    @Query("UPDATE PICDeveloperEntity w SET " +
            "w.personalNumber = :personalNumber, " +
            "w.personalName = :personalName " +
            "WHERE w.uuid = :uuid")
    int findByUuidAndUpdate(
            UUID uuid,
            String personalNumber,
            String personalName
    );

    //Deleting data PIC Developer by UUID
    @Modifying
    @Transactional
    @Query("DELETE FROM PICDeveloperEntity w WHERE w.uuid = :uuid")
    int findByUuidAndDelete(UUID uuid);
}

