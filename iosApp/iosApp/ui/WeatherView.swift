//
//  WeatherView.swift
//  iosApp
//
//  Created by Himanshu Gaur on 09/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct WeatherView: View {
    
    @ObservedObject var viewModelWrapper = WeatherVIewModelWrapper()
    
    var iosLocationService : IosLocationService = IosLocationService()
    
    var body: some View {
        ZStack{
            Color.blue.edgesIgnoringSafeArea(.all)
            
            if(!viewModelWrapper.uiState.error.isEmpty){
                Text(viewModelWrapper.uiState.error)
            }
            
            if(viewModelWrapper.uiState.currentWeather != nil){
                VStack{
                    AsyncImage(url: URL(string: viewModelWrapper.uiState.currentWeather!.iconUrl),
                               content: {image in image.resizable().frame(width: 200,height: 200)
                        .clipped()},
                               placeholder: {ProgressView()})
                    
                    Text(format(string: viewModelWrapper.uiState.currentWeather!.temperature))
                        .font(.system(size: 60))
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    
                    Text(viewModelWrapper.uiState.currentWeather!.name)
                        .font(.system(size: 40))
                        .fontWeight(.medium)
                        .foregroundColor(.white)
                    
                    if(viewModelWrapper.uiState.forecastInfo != nil){
                        
                        ScrollView{
                            
                            Text("Upcoming forecast info")
                                .font(.title)
                                .fontWeight(.bold)
                                .foregroundColor(.white)
                            
                            LazyVStack(content:{
                                
                                ForEach(viewModelWrapper.uiState.forecastInfo ?? [] , id: \.self){item in
                                    
                                    HStack{
                                        Text(item.date)
                                        
                                        Spacer()
                                        
                                        Text(format(string: item.temperature))
                                        
                                        Spacer()
                                        
                                        AsyncImage(url: URL(string: item.iconUrl), content: {image in image.resizable().frame(width: 30,height: 30).clipped()}, placeholder: {ProgressView()})
                                    }.padding()
                                        .frame(width: .infinity,alignment: .leading)
                                        .background(.white)
                                        .clipShape(RoundedRectangle(cornerRadius: 12))
                                        .padding(.horizontal , 12)
                                }
                                
                            }).frame(width: .infinity,alignment: .top)
                            
                        }.frame(width: .infinity)
                        
                    }
                    
                }
                
                
                
            }
            
            
        }
        .onAppear(perform: {
            if(iosLocationService.isPermissionGranted()){
                let location = iosLocationService.getLocation()
                if(location != nil){
                    viewModelWrapper.viewModel.getCurrentWeatherInfo(lat: location!.latitude, long: location!.longitude)
                    viewModelWrapper.viewModel.getForecastInfo(lat: location!.latitude, long: location!.longitude)
                }
            }else{
                iosLocationService.requestLocationPermission(granted: {value in
                    
                    if(value.boolValue){
                        let location = iosLocationService.getLocation()
                        if(location != nil){
                            viewModelWrapper.viewModel.getCurrentWeatherInfo(lat: location!.latitude, long: location!.longitude)
                            viewModelWrapper.viewModel.getForecastInfo(lat: location!.latitude, long: location!.longitude)
                        }
                    }
                }
                )
            }
        })
        
    }
}

#Preview {
    WeatherView()
}


func format(string:String) -> String{
    
    let float = Float(string)
    return String(format: "%.2f", float!)
    
}
