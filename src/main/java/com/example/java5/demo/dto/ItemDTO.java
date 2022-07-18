package com.example.java5.demo.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    int maSp;
    int soLuong;
    String title;
    float price;
    String image;

    // @Override
    // public int hashCode() {
    // int hash = 5;
    // hash = 43 * hash + this.maSp;
    // hash = 43 * hash + this.soLuong;
    // hash = 43 * hash + Objects.hashCode(this.title);
    // hash = 43 * hash + Objects.hashCode(this.price);
    // hash = 43 * hash + Objects.hashCode(this.image);
    // return hash;
    // }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemDTO other = (ItemDTO) obj;
        if (this.maSp != other.maSp) {
            return false;
        }
        return true;
    }

}
