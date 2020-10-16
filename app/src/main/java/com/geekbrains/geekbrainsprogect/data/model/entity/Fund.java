package com.geekbrains.geekbrainsprogect.data.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "fund",
  foreignKeys = @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id", onDelete = CASCADE))
public class Fund {
    @PrimaryKey
    long id;
    @ColumnInfo(name = "product_id")
    long productId;
    double balance;

    public Fund(long productId, double balance)
    {
        this.productId = productId;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }
    public double getBalance() {
        return balance;
    }

}
