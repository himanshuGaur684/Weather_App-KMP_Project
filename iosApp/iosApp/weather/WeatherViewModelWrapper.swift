//
//  WeatherViewModelWrapper.swift
//  iosApp
//
//  Created by Himanshu Gaur on 26/10/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class WeatherViewModelWrapper : ObservableObject {
    
    var viewModel : WeatherViewModel
    
    var task:Task<Void,Never>?
    
    @Published var uiState : UiState = UiState.init(isLoading: false, data: nil, error: "",weatherDetails: nil)
    
    init(){
        viewModel = ProvideViewModel.shared.getWeatherViewModel()
       
        task = Task{@MainActor [weak self] in
            if(self != nil){
                await collect(stateFlow: self!.viewModel.uiState, onEach: {value in self!.uiState = value})
            }
        }
    }
    
    deinit{
        task?.cancel()
    }
    
    
    
    
}
