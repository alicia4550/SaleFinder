package com.example.salefinder.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flyer {
//    @PrimaryKey
//    public int id;

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "flyer_run_id")
    public int flyer_run_id;

    @ColumnInfo(name = "flyer_type_id")
    public int flyer_type_id;

    @ColumnInfo(name = "postal_code")
    public String postal_code;

    @ColumnInfo(name = "locale")
    public String locale;

    @ColumnInfo(name = "language")
    public int language;

    @ColumnInfo(name = "premium")
    public boolean premium;

    @ColumnInfo(name = "relationship")
    public boolean relationship;

    @ColumnInfo(name = "priority")
    public int priority;

    @ColumnInfo(name = "organic_rank")
    public int organic_rank;

    @ColumnInfo(name = "path")
    public String path;

//    @ColumnInfo(name = "resolutions")
//    public int[] resolutions;

    @ColumnInfo(name = "resolutions_csv")
    public String resolutions_csv;

//    @ColumnInfo(name = "categories")
//    public String[] categories;

    @ColumnInfo(name = "categories_csv")
    public String categories_csv;

    @ColumnInfo(name = "width")
    public float width;

    @ColumnInfo(name = "height")
    public float height;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "merchant")
    public String merchant;

    @ColumnInfo(name = "merchant_id")
    public int merchant_id;

    @ColumnInfo(name = "merchant_logo")
    public String merchant_logo;

    @ColumnInfo(name = "popularity")
    public int popularity;

    @ColumnInfo(name = "thumbnail_url")
    public String thumbnail_url;

    @ColumnInfo(name = "premium_thumbnail_url")
    public String premium_thumbnail_url;

    @ColumnInfo(name = "thumbnail_hashed_key")
    public String thumbnail_hashed_key;

    @ColumnInfo(name = "valid_from")
    public String valid_from;

    @ColumnInfo(name = "valid_to")
    public String valid_to;

    @ColumnInfo(name = "available_from")
    public String available_from;

    @ColumnInfo(name = "available_to")
    public String available_to;

    @ColumnInfo(name = "updated_at")
    public String updated_at;

    @ColumnInfo(name = "web_indexed")
    public boolean web_indexed;

    @ColumnInfo(name = "seed")
    public float seed;

//    @ColumnInfo(name = "featured_items")
//    public String[] featured_items;

    @ColumnInfo(name = "analytics_payload")
    public String analytics_payload;

    @ColumnInfo(name = "is_store_select")
    public boolean is_store_select;

    @ColumnInfo(name = "display_type")
    public String display_type;

    @ColumnInfo(name = "storefront_premium_thumbnail_url")
    public String storefront_premium_thumbnail_url;

    @ColumnInfo(name = "stock_premium_thumbnail_url")
    public String stock_premium_thumbnail_url;

    @ColumnInfo(name = "storefront_carousel_premium_thumbnail_url")
    public String storefront_carousel_premium_thumbnail_url;

    @ColumnInfo(name = "storefront_carousel_organic_thumbnail_url")
    public String storefront_carousel_organic_thumbnail_url;

    @ColumnInfo(name = "storefront_sale_story")
    public String storefront_sale_story;

    @ColumnInfo(name = "storefront_logo_url")
    public String storefront_logo_url;

    @ColumnInfo(name = "sfml_hashed_key")
    public String sfml_hashed_key;

    @ColumnInfo(name = "budget_id")
    public int budget_id;

    @ColumnInfo(name = "cost_model_type")
    public String cost_model_type;

    @ColumnInfo(name = "auction_uuid")
    public String auction_uuid;

    @ColumnInfo(name = "publication_type")
    public int publication_type;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Flyer)) {
            return false;
        }

        Flyer that = (Flyer) other;

        return this.id == that.id
                && this.flyer_run_id == that.flyer_run_id
                && this.flyer_type_id == that.flyer_type_id
                && Objects.equals(this.postal_code, that.postal_code)
                && Objects.equals(this.locale, that.locale)
                && this.language == that.language
                && this.premium == that.premium
                && this.relationship == that.relationship
                && this.priority == that.priority
                && this.organic_rank == that.organic_rank
                && Objects.equals(this.path, that.path)
                && Objects.equals(this.resolutions_csv, that.resolutions_csv)
                && Objects.equals(this.categories_csv, that.categories_csv)
                && this.width == that.width
                && this.height == that.height
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.merchant, that.merchant)
                && this.merchant_id == that.merchant_id
                && Objects.equals(this.merchant_logo, that.merchant_logo)
                && this.popularity == that.popularity
                && Objects.equals(this.thumbnail_url, that.thumbnail_url)
                && Objects.equals(this.premium_thumbnail_url, that.premium_thumbnail_url)
                && Objects.equals(this.thumbnail_hashed_key, that.thumbnail_hashed_key)
                && Objects.equals(this.valid_from, that.valid_from)
                && Objects.equals(this.valid_to, that.valid_to)
                && Objects.equals(this.available_from, that.available_from)
                && Objects.equals(this.available_to, that.available_to)
                && Objects.equals(this.updated_at, that.updated_at)
                && this.web_indexed == that.web_indexed
                && this.seed == that.seed
                && Objects.equals(this.analytics_payload, that.analytics_payload)
                && this.is_store_select == that.is_store_select
                && Objects.equals(this.display_type, that.display_type)
                && Objects.equals(this.storefront_premium_thumbnail_url, that.storefront_premium_thumbnail_url)
                && Objects.equals(this.stock_premium_thumbnail_url, that.stock_premium_thumbnail_url)
                && Objects.equals(this.storefront_carousel_premium_thumbnail_url, that.storefront_carousel_premium_thumbnail_url)
                && Objects.equals(this.storefront_carousel_organic_thumbnail_url, that.storefront_carousel_organic_thumbnail_url)
                && Objects.equals(this.storefront_sale_story, that.storefront_sale_story)
                && Objects.equals(this.storefront_logo_url, that.storefront_logo_url)
                && Objects.equals(this.sfml_hashed_key, that.sfml_hashed_key)
                && this.budget_id == that.budget_id
                && Objects.equals(this.cost_model_type, that.cost_model_type)
                && Objects.equals(this.auction_uuid, that.auction_uuid)
                && this.publication_type == that.publication_type;
    }
}
