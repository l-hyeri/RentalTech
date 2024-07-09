package rentaltech.electronics.dto;

import lombok.Getter;
import lombok.Setter;
import rentaltech.electronics.constant.RentalStatus;
import rentaltech.electronics.entity.Rental;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RentalListDto {

    private Long rentalItemId;
    private String rentalDate;
    private RentalStatus rentalStatus;
    private List<RentalItemDto> rentalItemDtoList = new ArrayList<>();

    public RentalListDto(Rental rental) {
        this.rentalItemId = rental.getId();
        this.rentalDate = rental.getRentalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.rentalStatus = rental.getRentalStatus();
    }

    public void addRentalItemDto(RentalItemDto rentalItemDto) {
        rentalItemDtoList.add(rentalItemDto);
    }
}
