package org.gkid.ordersbyfilter;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "")
public interface FeignController {

}


@FeignClient(name = "findall-employees")
public interface MyFeignClient {
    /*
     1. Url
     2. INPUT
     3. Response
     */

    @GetMapping("/employees/{org_id}")
    List<Employee> findAllEmployeesByOrgFromClient(@PathVariable int org_id);
}