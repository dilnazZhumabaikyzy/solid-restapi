package com.example.solidbanksb.model.Account;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

//    @Query("SELECT MAX(CAST(SUBSTR(a.id, 5) AS DECIMAL)) FROM Account a")
//

    @Query("SELECT * FROM account WHERE client_id = :client_id AND accountType = :account_type")
    List<Account> findByClientIdAndAccountType(@Param("client_id") String client_id, @Param("account_type") String accountType);

    @Query("SELECT * FROM account WHERE client_id = :client_id AND id = :id")
    Account findByClientIdAndId(@Param("client_id") String client_id, @Param("id") String id);

    List<Account> findByClientId(String client_id);

}