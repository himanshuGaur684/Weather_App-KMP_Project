//
//  WeatherView.swift
//  iosApp
//
//  Created by Himanshu Gaur on 26/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct WeatherView: View {
    
    @ObservedObject var weatherViewModelWrapper = WeatherViewModelWrapper()
    
    let iosLocationService : IosLocationService = IosLocationService()
    
    var body: some View {
        ZStack{
            Color.blue.edgesIgnoringSafeArea(.all)
            
            if(weatherViewModelWrapper.uiState.isLoading){
                ProgressView()
            }
            
            if(!weatherViewModelWrapper.uiState.error.isEmpty){
                Text(weatherViewModelWrapper.uiState.error)
            }
            
            if(weatherViewModelWrapper.uiState.data != nil){
                VStack{
                    AsyncImage(url:URL(string: DataUtils.shared.getImageUrl(iconId: weatherViewModelWrapper.uiState.data!.icon) ) , content: {image in image.resizable().aspectRatio(contentMode: /*@START_MENU_TOKEN@*/.fill/*@END_MENU_TOKEN@*/).frame(width:250,height: 200).clipped()}, placeholder: {ProgressView()})
                    
                    Text(weatherViewModelWrapper.uiState.data!.country)
                        .font(.system(size: 36))
                        .fontWeight(.medium)
                        .foregroundColor(.white)
                    
                    Text(String(weatherViewModelWrapper.uiState.data!.temperature)+" C")
                        .font(.system(size: 60))
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    
                    if(weatherViewModelWrapper.uiState.weatherDetails != nil){

                        ScrollView{
                            
                            Text("Last 5 days forecast")
                                .font(.largeTitle)
                                .foregroundColor(.white)
                                .fontWeight(.medium)
                                .padding()
                            
                            
                            LazyVStack(content: {
                                ForEach(weatherViewModelWrapper.uiState.weatherDetails ?? [],id:\.self){item in
                                    HStack(content: {
                                       
                                        Text(DataUtils.shared.convertDate(date:item.time))
                                            
                                        Spacer()
                                        
                                        Text(DataUtils.shared.floatUpToTwoDecimalPlaces(float: item.feelsLike)+" C")
                                            
                                        
                                        Spacer()
                                        
                                        AsyncImage(url: URL(string: DataUtils.shared.getImageUrl(iconId: item.icon)), content: {image in image.resizable().frame(width: 30,height: 30).clipped()}, placeholder: { ProgressView() })
                                        
                                    })
                                    .padding()
                                    .frame(maxWidth: .infinity,alignment: .leading)
                                    .background(.white)
                                    .clipShape(RoundedRectangle(cornerRadius: 12))
                                    .padding(.horizontal,12)
                                }
                            })
                            .frame(maxWidth: .infinity,alignment: .top)
                        }.frame(width: .infinity)
                    }
                }
            }
        }
        .onAppear(perform: {
            if(iosLocationService.isPermissionGranted()){
                let location = iosLocationService.getLocation()
                if(location != nil){
                    weatherViewModelWrapper.viewModel.getWeather(lat: location!.latitude, lon: location!.longitude)
                    weatherViewModelWrapper.viewModel.getWeatherDetails(lat: location!.latitude, lon: location!.longitude)
                }
            }else{
                iosLocationService.requestLocationPermission(granted: { value in
                    if(value.boolValue){
                        let location = iosLocationService.getLocation()
                        
                        if(location != nil){
                            weatherViewModelWrapper.viewModel.getWeather(lat: location!.latitude, lon: location!.longitude)
                            weatherViewModelWrapper.viewModel.getWeatherDetails(lat: location!.latitude, lon: location!.longitude)
                        }
                    }
                })
            }
        })
    }
}

#Preview {
    WeatherView()
}
