package tw.brad.spring4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring4.entity.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, Long>{

}
