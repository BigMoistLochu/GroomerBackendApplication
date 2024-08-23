package aplikacja.groomerbackend.controllers;

import org.springframework.boot.web.server.Cookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    //router.post("/register-employee",authGuard,adminGuard,uploadFile.single("avatar"),adminControllers.registerEmployee);
    //router.get("/reservations",authGuard, adminGuard, adminControllers.getReservations);
    //router.get("/employees",authGuard, adminGuard,adminControllers.getEmployees);
    //router.get("/customers",authGuard, adminGuard,adminControllers.getCustomers);
    //router.get("/service/:id",authGuard, adminGuard, adminControllers.getServiceDetails);
    //router.put("/update/service/:id",authGuard,adminGuard,adminControllers.updateServiceDetails);
    //router.get("/auth/adminValidate",authGuard,adminGuard,adminControllers.validateToken);
    //router.get("/employee/:id",authGuard,adminGuard,adminControllers.getEmployeeDetails);

    @GetMapping("/customers")
    public String getCustomers(){
        return "test";
    }
}
