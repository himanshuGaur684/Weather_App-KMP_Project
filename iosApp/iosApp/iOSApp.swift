import SwiftUI
import shared

@main
struct iOSApp: App {
    init(){KoinDiSetupKt.doInitKoin()}
	var body: some Scene {
		WindowGroup {
			WeatherView()
		}
	}
}
