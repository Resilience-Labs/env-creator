generate:
	./gradlew goJF build
	cp build/libs/com.rlabs.secreter.jar app.jar
	git add .
	git commit -m "fix"
	git push