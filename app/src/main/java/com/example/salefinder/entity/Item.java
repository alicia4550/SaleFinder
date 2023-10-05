package com.example.salefinder.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "flyer_id")
    public int flyer_id;

    @ColumnInfo(name = "print_id")
    public String print_id;

    @ColumnInfo(name = "short_name")
    public String short_name;

    @ColumnInfo(name = "brand")
    public String brand;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "price")
    public String price;

    @ColumnInfo(name = "display_type")
    public int display_type;

    @ColumnInfo(name = "discount")
    public String discount;

    @ColumnInfo(name = "left")
    public float left;

    @ColumnInfo(name = "bottom")
    public float bottom;

    @ColumnInfo(name = "right")
    public float right;

    @ColumnInfo(name = "top")
    public float top;

    @ColumnInfo(name = "valid_from")
    public String valid_from;

    @ColumnInfo(name = "valid_to")
    public String valid_to;

    @ColumnInfo(name = "available_to")
    public String available_to;

    @ColumnInfo(name = "video_url")
    public String video_url;

    @ColumnInfo(name = "cutout_image_url")
    public String cutout_image_url;

    @ColumnInfo(name = "page_destination")
    public String page_destination;

    @ColumnInfo(name = "ttm_url")
    public String ttm_url;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Item)) {
            return false;
        }

        Item that = (Item) other;

        return this.id == that.id
                && this.flyer_id == that.flyer_id
                && Objects.equals(this.print_id, that.print_id)
                && Objects.equals(this.short_name, that.short_name)
                && Objects.equals(this.brand, that.brand)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.price, that.price)
                && this.display_type == that.display_type
                && Objects.equals(this.discount, that.discount)
                && this.left == that.left
                && this.bottom == that.bottom
                && this.right == that.right
                && this.top == that.top
                && Objects.equals(this.valid_from, that.valid_from)
                && Objects.equals(this.valid_to, that.valid_to)
                && Objects.equals(this.available_to, that.available_to)
                && Objects.equals(this.cutout_image_url, that.cutout_image_url)
                && Objects.equals(this.page_destination, that.page_destination)
                && Objects.equals(this.ttm_url, that.ttm_url);
    }
}
