package com.todotresde.interbanking.coursemanagement.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * The interface User client.
 */
@FeignClient("user-service")
public interface UserClient {

    /**
     * Gets user names.
     *
     * @param userIdList the user id list
     * @return the user names
     */
    @RequestMapping(method = RequestMethod.POST, value="/service/names", consumes = "application/json")
    List<String> getUserNames(@RequestBody List<Long> userIdList);
}
