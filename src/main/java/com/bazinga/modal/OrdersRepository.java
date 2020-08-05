package com.bazinga.modal;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	Set<Orders> findAllByUser(User user);

}
