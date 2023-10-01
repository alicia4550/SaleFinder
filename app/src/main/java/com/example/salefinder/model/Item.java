package com.example.salefinder.model;

public class Item {
    int id;
    int flyer_id;
    int print_id;
    String short_name;
    String brand;
    String name;
    String price;
    int display_type;
    String discount;
    float left;
    float bottom;
    float right;
    float top;
    String valid_from;
    String valid_to;
    String available_to;
    String video_url;
    String[] text_areas;
    String cutout_image_url;
    String page_destination;
    String ttm_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlyer_id() {
        return flyer_id;
    }

    public void setFlyer_id(int flyer_id) {
        this.flyer_id = flyer_id;
    }

    public int getPrint_id() {
        return print_id;
    }

    public void setPrint_id(int print_id) {
        this.print_id = print_id;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(int display_type) {
        this.display_type = display_type;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public String getValid_from() {
        return valid_from;
    }

    public void setValid_from(String valid_from) {
        this.valid_from = valid_from;
    }

    public String getValid_to() {
        return valid_to;
    }

    public void setValid_to(String valid_to) {
        this.valid_to = valid_to;
    }

    public String getAvailable_to() {
        return available_to;
    }

    public void setAvailable_to(String available_to) {
        this.available_to = available_to;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String[] getText_areas() {
        return text_areas;
    }

    public void setText_areas(String[] text_areas) {
        this.text_areas = text_areas;
    }

    public String getCutout_image_url() {
        return cutout_image_url;
    }

    public void setCutout_image_url(String cutout_image_url) {
        this.cutout_image_url = cutout_image_url;
    }

    public String getPage_destination() {
        return page_destination;
    }

    public void setPage_destination(String page_destination) {
        this.page_destination = page_destination;
    }

    public String getTtm_url() {
        return ttm_url;
    }

    public void setTtm_url(String ttm_url) {
        this.ttm_url = ttm_url;
    }
}
