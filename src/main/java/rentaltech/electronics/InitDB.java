package rentaltech.electronics;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import rentaltech.electronics.constant.ItemStockStatus;
import rentaltech.electronics.constant.Role;
import rentaltech.electronics.entity.Item;
import rentaltech.electronics.entity.ItemImg;
import rentaltech.electronics.entity.Member;

/**
 * 관리자 관련 정보를 미리 DB에 자동 저장하도록 구현
 */

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember();
            em.persist(member);

            Item item1=createItem(111L,"tv1",1000,2,"tv1번 상품",20240526);
            em.persist(item1);
        }

        public void dbInit2() {
            Item item1=createItem(222L,"aircondition",2000,3,"에어컨1번 상품",20240527);
            em.persist(item1);
        }

        private Member createMember() {
            Member member = new Member();
            member.setPw("1234");
            member.setName("manager");
            member.setPhone("010-0000-0000");
            member.setAddress("관리자 주소");
            member.setMail("manager@gmail.com");
            member.setRole(Role.ADMIN);

            return member;
        }

        private Item createItem(Long serialNum, String name, int price, int stock, String details,int period) {
            Item item = new Item();
            item.setSerialNum(serialNum);
            item.setItem_name(name);
            item.setPrice(price);
            item.setStockStatus(ItemStockStatus.SELL);
            item.setStock(stock);
            item.setDetails(details);
            item.setPeriod(period);

            return item;
        }
    }

}
