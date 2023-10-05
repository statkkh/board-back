package com.kimkh.boardbackproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.kimkh.boardbackproject.entity.primaryKey.FavoritePk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="favorite")
@Table(name="favorite")
@IdClass(FavoritePk.class)
public class FavoriteEntity {
    // description: 복합키 일 때 두컬럼 ID 설정
    @Id
    private String userEmail;
    @Id
    private int boardNumber;

}
