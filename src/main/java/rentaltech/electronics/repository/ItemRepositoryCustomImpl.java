package rentaltech.electronics.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.thymeleaf.util.StringUtils;
import rentaltech.electronics.dto.ItemSearchDto;
import rentaltech.electronics.dto.MainItemDto;
import rentaltech.electronics.dto.QMainItemDto;
import rentaltech.electronics.entity.QItem;
import rentaltech.electronics.entity.QItemImg;

import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private JPAQueryFactory queryFactory;

    // 생성자 DI를 통해서 JPAQueryFactory(EntityManager) 주입
    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto) {

        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        List<MainItemDto> mainResult = queryFactory
                .select(
                        new QMainItemDto(
                                item.serialNum,
                                item.item_name,
                                item.price,
                                itemImg.imgUrl,
                                item.stockStatus)
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repreImg.eq("Y"))
                .where(itemNameLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.serialNum.desc())
                .fetchResults().getResults();

        return mainResult;
    }

    private BooleanExpression itemNameLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.item_name.like("%" + searchQuery + "%");
    }
}
