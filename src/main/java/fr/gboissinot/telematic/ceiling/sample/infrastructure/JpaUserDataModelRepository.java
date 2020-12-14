package fr.gboissinot.telematic.ceiling.sample.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaUserDataModelRepository extends JpaRepository<UserDataModel, Long> {
}
