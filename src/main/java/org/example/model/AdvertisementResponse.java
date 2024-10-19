package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Advertisement;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementResponse {
    private List<Advertisement> advertisementList;
}
