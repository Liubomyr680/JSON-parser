package com.example.Parser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "json_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermitForEmissionsOfPollutants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "edrpou")
    private String edrpou;

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "data_type")
    private String data_type;

    @Column(name = "validity")
    private String validity;

    @Column(name = "legal_address")
    private String legal_address;

    @Column(name = "actual_address")
    private String actual_address;
}
