package internetdonation;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DonationRepository extends PagingAndSortingRepository<Donation, Long>{


}