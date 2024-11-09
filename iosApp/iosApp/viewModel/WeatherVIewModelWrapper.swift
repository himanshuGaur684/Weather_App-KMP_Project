//
//  WeatherVIewModelWrapper.swift
//  iosApp
//
//  Created by Himanshu Gaur on 09/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared


class WeatherVIewModelWrapper : ObservableObject{
    
    var viewModel: WeatherViewModel
    
    init(){
        self.viewModel = ProvideViewModel.shared.getWeatherViewModel()
        task = Task{@MainActor [weak self] in
            
            if(self != nil){
                await collect(stateFlow: self!.viewModel.uiState, onEach: {value in
                    self!.uiState = value
                })
            }
                
        }
    }
    
    @Published var uiState = UiState.init(error: "", currentWeather: nil, forecastInfo: nil)
    
    var task:Task<Void,Never>?
    
    deinit{
        task?.cancel()
    }
    
}

