package com.example.java5.demo.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    ArrayList<ItemDTO> carts = new ArrayList<>();

    public boolean add(ItemDTO item) {
        try {
            if (carts.contains(item)) {
                ItemDTO current = carts.get(carts.indexOf(item));
                current.setSoLuong(current.getSoLuong() + 1);
                if (current.getSoLuong() >= 10) {
                    current.setSoLuong(10);
                }
            } else {
                carts.add(item);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(ItemDTO item) {
        try {
            if (carts.contains(item)) {
                int vt = carts.indexOf(item);
                carts.remove(vt);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean dete(ItemDTO item) {
        try {
            if (carts.contains(item)) {
                ItemDTO currentItem = carts.get(carts.indexOf(item));
                currentItem.setSoLuong(currentItem.getSoLuong() - 1);
                if (currentItem.getSoLuong() == 0) {
                    // currentItem.setSoLuong(1);
                    carts.remove(item);
                }

            } else {
                carts.remove(item);
            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public float getTongTien() {
        float tongTien = 0;
        for (ItemDTO item : carts) {
            tongTien += item.getSoLuong() * item.getPrice();
        }
        return tongTien;
    }

    public int getSize() {
        return carts.size();
    }
}