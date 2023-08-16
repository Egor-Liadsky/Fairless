import SwiftUI
import shared

struct ContentView: View {

	var body: some View {
        Button {
            NavigatorImpl().navigateToMain()
        } label: {
            Text("Navigate to Main")
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
