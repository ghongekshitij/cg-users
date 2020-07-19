package com.indisputable.user.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, String> {

    public UserInformation findByMobile(Long mobile);
}
