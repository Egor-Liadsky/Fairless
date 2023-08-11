//
//  DocumentItem.swift
//  iosApp
//
//  Created by Егор Лядский on 2023-08-08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProductView: View {
    
    let product: ProductData
    
    var body: some View {
            HStack{
                AsyncImage(url: URL(string: .base_url + (product.image?.url ?? ""))) { image in
                    image
                        .resizable()
                        .scaledToFit()
                        .frame(width: 90, height: 120)
                        .scaledToFit()
                } placeholder: {
                    Rectangle()
                        .frame(width: 90, height: 120)
                        .background(Color.Background.borderGray.cornerRadius(16))
                        .cornerRadius(16)
                }
                .padding(.leading, 10)
                
                
                VStack (alignment: .leading) {
                    Text(product.name ?? "")
                        .font(.custom("Qanelas-Semibold", size: 12))
                        .foregroundColor(Color.black)

                    
                    if let price = product.sale_price {
                        let percent = (100 - ((Int(truncating: product.sale_price!) * 100) / Int(truncating: product.sale_old_price!)))
                        HStack(spacing: 7) {
                            Text(price.description)
                                .font(.custom("Qanelas-Heavy", size: 17))
                                .foregroundColor(.orangePrimary)
                            
                            Text(product.sale_old_price?.description ?? "")
                                .font(.custom("Qanelas-Regular", size: 10))
                                .foregroundColor(Color.black)
                                .strikethrough()
                            
                            Text("(-\(percent)%)")
                                .font(.custom("Qanelas-Regular", size: 10))
                                .foregroundColor(Color.black)
                            
                            Spacer()
                        }
                        .padding(.top, 5)
                    }
                    
                    HStack {
                        
                        if let shop = product.shop {
                            Text(shop.name ?? "")
                                .font(.custom("Qanelas-Regular", size: 9))
                                .foregroundColor(Color.black)
                            Spacer()
                        }
                        
                        HStack(spacing: 10) {
                            HStack(spacing: 8){
                                Image("ic_like")
                                    .resizable()
                                    .frame(width: 14, height: 11)
                                Text(product.count_likes?.description ?? "0")
                                    .font(.custom("Qanelas-Light", size: 10))
                                    .foregroundColor(Color.black)
                            }

                            HStack(spacing: 8) {
                                Image("ic_dislike")
                                    .resizable()
                                    .frame(width: 14, height: 11)
                                Text(product.count_dislikes?.description ?? "0")
                                    .font(.custom("Qanelas-Light", size: 10))
                                    .foregroundColor(Color.black)
                            }
                        }
                    }
                    .padding(.top, 5)

                    Divider()
                    
                    HStack {
                        if let user = product.users_permissions_user {
                            Image("avatarka")
                                .resizable()
                                .frame(width: 16, height: 16)
                            Text(user.username ?? "")
                                .font(.custom("Qanelas-Semibold", size: 9))
                                .foregroundColor(Color.black)
                        }
                        
                        Spacer()
                        
                        RatingView(likes: product.count_likes ?? "0", dislikes: product.count_dislikes ?? "0")
                    }
                    
                    Divider()
                    
                    HStack(spacing: 10) {
                        HStack(spacing: 8) {
                            Image("ic_comments")
                                .resizable()
                                .frame(width: 14, height: 13)
                            Text(product.count_comments?.description ?? "0")
                                .font(.custom("Qanelas-Light", size: 10))
                                .foregroundColor(Color.black)
                        }
                        HStack(spacing: 8) {
                            Image("ic_views")
                                .resizable()
                                .frame(width: 14, height: 14)
                            Text(product.count_views?.description ?? "0")
                                .font(.custom("Qanelas-Light", size: 10))
                                .foregroundColor(Color.black)
                        }
                        Spacer()
                        
                        if let type = product.stock_type {
                            Text(type.name ?? "")
                                .font(.custom("Qanelas-Light", size: 10))
                                .foregroundColor(.orangePrimary)
                            Image("ic_thunder")
                                .resizable()
                                .frame(width: 8, height: 11)
                                .padding(.leading, 3)
                        }
                    }
                }
                .padding(.leading, 7)
                .padding(.trailing, 10)
            }
            .padding(.vertical, 12)
    }
}
