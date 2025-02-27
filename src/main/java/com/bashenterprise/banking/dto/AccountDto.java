package com.bashenterprise.banking.dto;
//
//import lombok.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class AccountDto {
//    private Long id;
//    private String accountHolderName;
//    private double balance;
//}

public record AccountDto(
        Long id,
        String accountHolderName,
        double balance){

}