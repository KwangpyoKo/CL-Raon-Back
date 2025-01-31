package coLaon.ClaonBack.center.dto;

import coLaon.ClaonBack.center.domain.Center;
import coLaon.ClaonBack.center.domain.HoldInfo;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CenterResponseDto {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String webUrl;
    private String instagramUrl;
    private String youtubeUrl;
    private List<CenterImgDto> imgList;
    private List<OperatingTimeDto> operatingTimeList;
    private String facilities;
    private List<ChargeDto> chargeList;
    private String chargeImg;
    private List<HoldInfoResponseDto> holdInfoList;
    private String holdInfoImg;
    private List<SectorInfoDto> sectorInfoList;

    private CenterResponseDto(
            String id,
            String name,
            String address,
            String tel,
            String webUrl,
            String instagramUrl,
            String youtubeUrl,
            List<CenterImgDto> imgList,
            List<OperatingTimeDto> operatingTimeList,
            String facilities,
            List<ChargeDto> chargeList,
            String chargeImg,
            List<HoldInfoResponseDto> holdInfoList,
            String holdInfoImg,
            List<SectorInfoDto> sectorInfoList
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.webUrl = webUrl;
        this.instagramUrl = instagramUrl;
        this.youtubeUrl = youtubeUrl;
        this.imgList = imgList;
        this.operatingTimeList = operatingTimeList;
        this.facilities = facilities;
        this.chargeList = chargeList;
        this.chargeImg = chargeImg;
        this.holdInfoList = holdInfoList;
        this.holdInfoImg = holdInfoImg;
        this.sectorInfoList = sectorInfoList;
    }

    public static CenterResponseDto from(Center center, List<HoldInfo> holdInfoList) {
        return new CenterResponseDto(
                center.getId(),
                center.getName(),
                center.getAddress(),
                center.getTel(),
                center.getWebUrl(),
                center.getInstagramUrl(),
                center.getYoutubeUrl(),
                center.getImgList().stream().map(CenterImgDto::from).collect(Collectors.toList()),
                center.getOperatingTime().stream().map(OperatingTimeDto::from).collect(Collectors.toList()),
                center.getFacilities(),
                center.getCharge().stream().map(ChargeDto::from).collect(Collectors.toList()),
                center.getChargeImg(),
                holdInfoList.stream().map(HoldInfoResponseDto::from).collect(Collectors.toList()),
                center.getHoldInfoImg(),
                center.getSectorInfo().stream().map(SectorInfoDto::from).collect(Collectors.toList())
        );
    }
}
