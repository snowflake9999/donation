
package internetdonation.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="donation", url="${api.url.donation}")
public interface DonationService {

    @RequestMapping(method= RequestMethod.POST, path="/donations")
    public void pay(@RequestBody Donation donation);

}