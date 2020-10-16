package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.text.DecimalFormat;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;
@Entity(tableName = "product_transaction")
public class ProductTransaction {
    @PrimaryKey
    long id;
    @ColumnInfo(name = "product_id")
    @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id", onDelete = CASCADE)
    long productId;
    @ColumnInfo(name = "contractor_id")
    @ForeignKey(entity = Contractor.class, parentColumns = "id", childColumns = "contractor_id")
    long contractorId;
    @ColumnInfo(name = "user_id")
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")
    long userId;
    Date date;
    double quantity;
    private String comment;


    public ProductTransaction(long productId, long contractorId,long userId, double count, String comment) {
        this.productId = productId;
        this.contractorId = contractorId;
        this.userId = userId;
        quantity = count;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getQuantity() {
        return quantity;
    }
    public String getStringQuantity()
    {
        return new DecimalFormat("#.##").format(quantity);
    }
    public Date getDate() {
         return date;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
