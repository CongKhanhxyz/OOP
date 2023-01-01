package com.example.spring08fpt.controllers;

import com.example.spring08fpt.model.Customer;
import com.example.spring08fpt.model.LSGiaoDich;
import com.example.spring08fpt.model.MayATM;
import com.example.spring08fpt.respository.CustomerRepository;
import com.example.spring08fpt.respository.LSGiaoDichRepository;
import com.example.spring08fpt.respository.MayATMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class HomeController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LSGiaoDichRepository lsGiaoDichRepository;
    @Autowired
    MayATMRepository mayATMRepository;

    private final Path storageFolder = Path.of("uploads");

    @GetMapping
    public String viewer()
    {
        return "index";
    }
    @GetMapping("/add")
    public void render(Model model )
    {
        String x  = "alo alo";
        model.addAttribute("text", x);
    }
    @GetMapping("/login")
    public String getPass(Model model)
    {
        return "/login";
    }

    int soDu;
    Customer currentCustomer = new Customer();
    @PostMapping("/save")
    public String postPass( @RequestParam("password") String password,
                           @RequestParam("username") String username,
                           ModelMap model  )
    {
       for (Customer customer : customerRepository.findAll())
       {
           if (customer.getPassword().equals(password) && customer.getName().equals(username))
           {
               currentCustomer = customer;
               soDu = currentCustomer.getSoDu();
               return "success";
           }
       }
       return "nosuccess";
    }

    @GetMapping("/rutTien2")
    public String rutTien()
    {
        return "rutTien";
    }
//    int soDuMoi;
    @PostMapping("/rut")
    public String rutTien(@RequestParam(value = "money", required = false) int money, Model model) {

        if (money % 10000 == 0) {
            if (money > 0 && money < soDu) {
                soDu = soDu - money;
                currentCustomer.setSoDu(soDu);
                LSGiaoDich newLsGiaoDich = new LSGiaoDich();
                newLsGiaoDich.setSoTienRut(money);
                newLsGiaoDich.setCreateDate(java.time.LocalDateTime.now());
                newLsGiaoDich.setCustomer(currentCustomer);
                List<LSGiaoDich> DSLSGiaoDich = lsGiaoDichRepository.findAll();
                DSLSGiaoDich.add(newLsGiaoDich);
                currentCustomer.setDSLSGiaoDich(DSLSGiaoDich);
                lsGiaoDichRepository.save(newLsGiaoDich);
                Optional<MayATM> mayATM = mayATMRepository.findById(1L);
                mayATM.get().setSoTien(mayATM.get().getSoTien() - money);
                customerRepository.save(currentCustomer);
                //String fiveHunderdThousand1 = Paths.get("upload", file.getOriginalFilename());

                model.addAttribute("money", money);
                int fiveHunderdThousand = 0;
                int twoHunderdThousand = 0;
                int oneHunderdThousand = 0;
                int fiftyThousand = 0;
                int tweentyThousand = 0;
                int tenThousand = 0;
                do {
                    if (money >= 500000) {
                        fiveHunderdThousand = money / 500000;
                        money = money % 500000;
                        model.addAttribute("l", fiveHunderdThousand);
                        model.addAttribute("a", 500000);
                    }
                    if (money >= 200000) {
                        twoHunderdThousand = money / 200000;
                        money = money % 200000;
                        model.addAttribute("h", twoHunderdThousand);
                        model.addAttribute("b", 200000);

                    }
                    if (money >= 100000) {
                        oneHunderdThousand = money / 100000;
                        money = money % 100000;
                        model.addAttribute("i", oneHunderdThousand);
                        model.addAttribute("c", 100000);

                    }
                    if (money >= 50000) {
                        fiftyThousand = money / 50000;
                        money = money % 50000;
                        model.addAttribute("k", fiftyThousand);
                        model.addAttribute("d", 50000);

                    }
                    if (money >= 20000) {
                        tweentyThousand = money / 20000;
                        money = money % 20000;
                        model.addAttribute("m", tweentyThousand);
                        model.addAttribute("e", 20000);

                    }
                    if (money >= 10000) {
                        tenThousand = money / 10000;
                        money = money % 10000;
                        model.addAttribute("n", tenThousand);
                        model.addAttribute("f", 10000);

                    }
                } while (money != 0);
                return "rutThanhCong";
            } else {
                return "KhongDuTien";
            }
        }
        {
            return "KhongDuTien";
        }
    }
    @GetMapping("/CheckSoDu")
    public String getSoDu(Model model)
    {
        model.addAttribute("soDu", soDu);
        return "soDu";
    }
    // change pass word
    @GetMapping("/doiMK")
    public String doiMatKhau()
    {
        return "doiMatKhau";
    }
    @PostMapping("/savePassword")
    public String doiMatKhau(@RequestParam(value = "newPassword", required = false) String  password,
                             @RequestParam(value = "password2", required = false) String password2 )
    {
        if (password.equals(password2))
        {
            currentCustomer.setPassword(password);
            customerRepository.save(currentCustomer);
            return "doiThanhCong";
        }
        return "khongThanhCong";
    }
    @GetMapping("/success")
    public String returnHome()
    {
        return "success";
    }
    @GetMapping("/exit")
    public String exit()
    {
        return "login";
    }
    @GetMapping("/lsgd")
    public String LSGiaoDich(Model model)
    {
        List<LSGiaoDich> lsGiaoDichList = lsGiaoDichRepository.findAllByCustomerId(currentCustomer.getId());
        model.addAttribute("DSLSGiaoDich", lsGiaoDichList);
        int size =  lsGiaoDichList.size();
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= size; i++)
        {
            list.add(i);
        }
        model.addAttribute("list", list);
        return "LSGiaoDich";
    }
}