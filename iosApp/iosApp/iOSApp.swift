import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        ModulesKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			MainNavigation()
		}
	}
}
