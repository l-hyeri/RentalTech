package rentaltech.electronics.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalItem is a Querydsl query type for RentalItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalItem extends EntityPathBase<RentalItem> {

    private static final long serialVersionUID = 1048414071L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalItem rentalItem = new QRentalItem("rentalItem");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final NumberPath<Integer> rentalPrice = createNumber("rentalPrice", Integer.class);

    public final QRental rentals;

    public QRentalItem(String variable) {
        this(RentalItem.class, forVariable(variable), INITS);
    }

    public QRentalItem(Path<? extends RentalItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalItem(PathMetadata metadata, PathInits inits) {
        this(RentalItem.class, metadata, inits);
    }

    public QRentalItem(Class<? extends RentalItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item")) : null;
        this.rentals = inits.isInitialized("rentals") ? new QRental(forProperty("rentals"), inits.get("rentals")) : null;
    }

}

