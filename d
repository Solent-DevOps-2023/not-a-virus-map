[1mdiff --git a/.github/workflows/deploy.yaml b/.github/workflows/deploy.yaml[m
[1mnew file mode 100644[m
[1mindex 0000000..a28b389[m
[1m--- /dev/null[m
[1m+++ b/.github/workflows/deploy.yaml[m
[36m@@ -0,0 +1,67 @@[m
[32m+[m[32mname: Deploy to Azure VM[m
[32m+[m
[32m+[m[32mon:[m
[32m+[m[32m  push:[m
[32m+[m[32m    branches:[m
[32m+[m[32m      - main[m
[32m+[m
[32m+[m[32mjobs:[m
[32m+[m[32m  deploy:[m
[32m+[m[32m    runs-on: ubuntu-latest[m
[32m+[m
[32m+[m[32m    steps:[m
[32m+[m[32m      - name: Checkout Repository[m
[32m+[m[32m        uses: actions/checkout@v2[m
[32m+[m
[32m+[m[32m      - name: Create SSH directory if not exists[m
[32m+[m[32m        run: mkdir -p ~/.ssh[m
[32m+[m[41m  [m
[32m+[m[32m      - name: Deploy to Azure VM[m
[32m+[m[32m        run: |[m
[32m+[m[32m          AZURE_VM_IP=20.117.224.131[m
[32m+[m[32m          AZURE_VM_USERNAME=azureuser[m[41m [m
[32m+[m[32m          GITHUB_REPO_PATH="$PWD"  # Current working directory of your GitHub Actions runner[m
[32m+[m[32m          GITHUB_BRANCH="main"[m
[32m+[m[32m          DESTINATION_DIRECTORY="/home/azureuser/DevOps"[m
[32m+[m[41m  [m
[32m+[m[32m          ssh-keyscan -H $AZURE_VM_IP >> ~/.ssh/known_hosts[m
[32m+[m[41m  [m
[32m+[m[32m          # Copy the SSH key from GitHub secrets to a file[m
[32m+[m[32m          echo "$AZURE_VM_SSH_KEY" > ~/.ssh/id_rsa[m
[32m+[m[32m          chmod 600 ~/.ssh/id_rsa[m
[32m+[m[41m  [m
[32m+[m[32m          # Clone or pull the latest changes from the GitHub repository[m
[32m+[m[32m          # if [ -d "$GITHUB_REPO_PATH" ]; then[m
[32m+[m[32m          #     cd "$GITHUB_REPO_PATH"[m
[32m+[m[41m  [m
[32m+[m[32m          # # Alt code using git fetch[m
[32m+[m[32m          # #     git fetch[m
[32m+[m[32m          # #     git reset --hard origin/$GITHUB_BRANCH[m
[32m+[m[32m          # # else[m
[32m+[m[32m          # #     git clone -b $GITHUB_BRANCH --single-branch https://github.com/jrykns-org/not-a-virus-map.git $GITHUB_REPO_PATH[m
[32m+[m[32m          # # fi[m
[32m+[m[41m  [m
[32m+[m[32m          #     git pull origin $GITHUB_BRANCH[m
[32m+[m[32m          # else[m
[32m+[m[32m          #     git clone -b $GITHUB_BRANCH --single-branch https://github.com/jrykns-org/not-a-virus-map.git $GITHUB_REPO_PATH[m
[32m+[m[32m          # fi[m
[32m+[m[41m  [m
[32m+[m[32m          # Copy the updated files to the Azure VM using SCP[m
[32m+[m[32m          # scp -o StrictHostKeyChecking=no -i ~/.ssh/id_rsa -r $GITHUB_REPO_PATH/* $AZURE_VM_USERNAME@$AZURE_VM_IP:$DESTINATION_DIRECTORY[m
[32m+[m
[32m+[m[32m          # Perform Docker build on the Azure VM[m
[32m+[m[32m          # docker build -t your_image_name .[m
[32m+[m[32m          # Optionally push the image to a registry if needed[m
[32m+[m[32m          # docker push your_image_name[m
[32m+[m
[32m+[m[32m          git pull[m
[32m+[m[32m          rm -rf target/*[m
[32m+[m[32m          mvn clean install[m
[32m+[m[32m          sudo systemctl restart devops[m
[32m+[m
[32m+[m[41m          [m
[32m+[m[32m          # Fresh re-build web app[m
[32m+[m[32m          # mvn clean install[m
[32m+[m[41m          [m
[32m+[m[32m        env:[m[41m [m
[32m+[m[32m          AZURE_VM_SSH_KEY: ${{ secrets.AZURE_VM_SSH_KEY }}[m
[1mdiff --git a/.github/workflows/deploy.yml b/.github/workflows/deploy.yml[m
[1mdeleted file mode 100644[m
[1mindex 9ea72b6..0000000[m
[1m--- a/.github/workflows/deploy.yml[m
[1m+++ /dev/null[m
[36m@@ -1,38 +0,0 @@[m
[31m-name: Deploy to Azure VM[m
[31m-[m
[31m-on:[m
[31m-  push:[m
[31m-    branches:[m
[31m-      - main  # Adjust this branch based on your Git workflow[m
[31m-[m
[31m-jobs:[m
[31m-  deploy:[m
[31m-    runs-on: ubuntu-20.04  # Use an Ubuntu runner for Linux-based VMs[m
[31m-[m
[31m-    steps:[m
[31m-    - name: Checkout Repository[m
[31m-      uses: actions/checkout@v2[m
[31m-[m
[31m-    - name: Deploy to Azure VM[m
[31m-      run: |[m
[31m-        # Add your deployment script here[m
[31m-        # This script should copy your updated files to the Azure VM[m
[31m-        # Example: Use SSH to copy files to the VM[m
[31m-[m
[31m-        AZURE_VM_IP=20.117.224.131  # Replace with the actual IP address or hostname of your Azure VM[m
[31m-        AZURE_VM_USERNAME=azureuser  # Replace with the actual username of your Azure VM[m
[31m-        AZURE_VM_SSH_KEY="${{ secrets.AZURE_VM_SSH_KEY }}"  # Add this secret in your repository settings[m
[31m-        GITHUB_REPO_PATH="$PWD"  # This is the current working directory of your GitHub Actions runner[m
[31m-        GITHUB_BRANCH="main"  # Replace with the branch you want to deploy[m
[31m-        DESTINATION_DIRECTORY="/home/azureuser/DevOps"   # Replace with the directory on your Azure VM where you want to deploy the files[m
[31m-[m
[31m-        # Clone or pull the latest changes from the GitHub repository[m
[31m-        if [ -d "$GITHUB_REPO_PATH" ]; then[m
[31m-            cd "$GITHUB_REPO_PATH"[m
[31m-            git pull origin $GITHUB_BRANCH[m
[31m-        else[m
[31m-            git clone -b $GITHUB_BRANCH --single-branch https://github.com/jrykns-org/not-a-virus-map.git $GITHUB_REPO_PATH[m
[31m-        fi[m
[31m-[m
[31m-        # Copy the updated files to the Azure VM using SCP[m
[31m-        scp -o StrictHostKeyChecking=no -i <(echo "$AZURE_VM_SSH_KEY") -r $GITHUB_REPO_PATH/* $AZURE_VM_USERNAME@$AZURE_VM_IP:$DESTINATION_DIRECTORY[m
[1mdiff --git a/123312 b/123312[m
[1mdeleted file mode 100644[m
[1mindex f9661e8..0000000[m
[1m--- a/123312[m
[1m+++ /dev/null[m
[36m@@ -1 +0,0 @@[m
[31m-12313[m
[1mdiff --git a/codeReviewTEST3.py b/codeReviewTEST3.py[m
[1mdeleted file mode 100644[m
[1mindex 31c6863..0000000[m
[1m--- a/codeReviewTEST3.py[m
[1m+++ /dev/null[m
[36m@@ -1 +0,0 @@[m
[31m-asfafsd[m
[1mdiff --git a/codeReviewTesdtNewFile b/codeReviewTesdtNewFile[m
[1mdeleted file mode 100644[m
[1mindex ca3fce4..0000000[m
[1m--- a/codeReviewTesdtNewFile[m
[1m+++ /dev/null[m
[36m@@ -1 +0,0 @@[m
[31m-test code review[m
[1mdiff --git a/pom.xml b/pom.xml[m
[1mindex 494c977..7b4fe76 100644[m
[1m--- a/pom.xml[m
[1m+++ b/pom.xml[m
[36m@@ -40,12 +40,7 @@[m
       <dependency>[m
          <groupId>org.springframework.boot</groupId>[m
          <artifactId>spring-boot-starter-data-jpa</artifactId>[m
[31m-         <exclusions>[m
[31m-            <exclusion>[m
[31m-               <groupId>org.springframework.boot</groupId>[m
[31m-               <artifactId>spring-boot-starter-logging</artifactId>[m
[31m-            </exclusion>[m
[31m-         </exclusions>[m
[32m+[m[41m         [m
       </dependency>[m
       <!-- <dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-security</artifactId> </dependency> -->[m
 [m
[36m@@ -66,22 +61,18 @@[m
       <dependency>[m
          <groupId>org.springframework.boot</groupId>[m
          <artifactId>spring-boot-starter-web</artifactId>[m
[31m-         <exclusions>[m
[31m-            <exclusion>[m
[31m-               <groupId>org.springframework.boot</groupId>[m
[31m-               <artifactId>spring-boot-starter-logging</artifactId>[m
[31m-            </exclusion>[m
[31m-         </exclusions>[m
[32m+[m[41m         [m
[32m+[m[41m         [m
       </dependency>[m
[32m+[m[41m      [m
[32m+[m[41m      [m
 [m
[31m-    <dependency>[m
[31m-	<groupId>org.springframework.boot</groupId>[m
[31m-	<artifactId>spring-boot-starter-actuator</artifactId>[m
[31m-    </dependency>[m
[31m-    <dependency>[m
[32m+[m[32m<!--    DUBLICATE ! ! ![m[41m [m
[32m+[m	[32m<dependency>[m
 	<groupId>org.springframework.boot</groupId>[m
 	<artifactId>spring-boot-starter-web</artifactId>[m
[31m-    </dependency>[m
[32m+[m[32m    </dependency> -->[m
[32m+[m[41m	   [m
     <dependency>[m
 	<groupId>io.micrometer</groupId>[m
 	<artifactId>micrometer-registry-prometheus</artifactId>[m
[36m@@ -114,6 +105,10 @@[m
          <artifactId>springdoc-openapi-ui</artifactId>[m
          <version>${springdoc.version}</version>[m
       </dependency>[m
[32m+[m[41m      [m
[32m+[m[41m          [m
[32m+[m
[32m+[m[41m      [m
 [m
       <dependency>[m
          <groupId>javax.servlet</groupId>[m
[36m@@ -159,6 +154,9 @@[m
          <groupId>org.glassfish.jaxb</groupId>[m
          <artifactId>jaxb-runtime</artifactId>[m
       </dependency>[m
[32m+[m[41m      [m
[32m+[m[41m      [m
[32m+[m[41m      [m
       <!-- needed by Hibernate for java 11 -->[m
       <dependency>[m
          <groupId>org.javassist</groupId>[m
[36m@@ -185,8 +183,36 @@[m
       <dependency>[m
         <groupId>org.mindrot</groupId>[m
         <artifactId>jbcrypt</artifactId>[m
[31m-        <version>0.4</version> <!-- Check for the latest version on Maven Central -->[m
[32m+[m	[32m<version>0.4</version>[m
[32m+[m[32m    </dependency>[m
[32m+[m[32m<!--     <dependency>[m
[32m+[m[32m    <groupId>mysql</groupId>[m
[32m+[m[32m    <artifactId>mysql-connector-java</artifactId> -->[m
[32m+[m[32m    <!-- No version specified -->[m
[32m+[m[32m     <!-- <version>8.0.33</version> Use the version that matches your MySQL server version[m
[32m+[m[32m    </dependency> -->[m
[32m+[m[32m<!--     <dependency>[m
[32m+[m[32m    <groupId>org.hsqldb</groupId>[m
[32m+[m[32m    <artifactId>hsqldb</artifactId>[m
[32m+[m[32m    <version>2.5.1</version>[m
[32m+[m[32m</dependency> -->[m
[32m+[m[32m<!-- Jackson for JSON processing -->[m
[32m+[m[32m    <dependency>[m
[32m+[m[32m        <groupId>com.fasterxml.jackson.core</groupId>[m
[32m+[m[32m        <artifactId>jackson-databind</artifactId>[m
[32m+[m[32m        <version>2.12.2</version> <!-- Use the latest version available -->[m
[32m+[m[32m    </dependency>[m
[32m+[m[32m    <dependency>[m
[32m+[m[32m        <groupId>org.apache.logging.log4j</groupId>[m
[32m+[m[32m        <artifactId>log4j-api</artifactId>[m
[32m+[m[32m        <version>2.22.0</version>[m
[32m+[m[32m    </dependency>[m
[32m+[m[32m    <dependency>[m
[32m+[m[32m        <groupId>org.apache.logging.log4j</groupId>[m
[32m+[m[32m        <artifactId>log4j-core</artifactId>[m
[32m+[m[32m        <version>2.22.0</version>[m
     </dependency>[m
[32m+[m[41m [m
    </dependencies>[m
 [m
    <build>[m
[36m@@ -254,5 +280,11 @@[m
 [m
    </build>[m
 [m
[32m+[m[32m<repositories>[m
[32m+[m[32m    <repository>[m
[32m+[m[32m        <id>central</id>[m
[32m+[m[32m        <url>https://repo.maven.apache.org/maven2</url>[m
[32m+[m[32m    </repository>[m
[32m+[m[32m</repositories>[m
 [m
 </project>[m
[1mdiff --git a/src/main/java/UserAuthoriserService.java b/src/main/java/UserAuthoriserService.java[m
[1mnew file mode 100644[m
[1mindex 0000000..b0ed063[m
[1m--- /dev/null[m
[1m+++ b/src/main/java/UserAuthoriserService.java[m
[36m@@ -0,0 +1,92 @@[m
[32m+[m[32m/*[m
[32m+[m[32m * Licensed under the Apache License, Version 2.0 (the "License");[m
[32m+[m[32m * you may not use this file except in compliance with the License.[m
[32m+[m[32m * You may obtain a copy of the License at[m
[32m+[m[32m *[m
[32m+[m[32m *     http://www.apache.org/licenses/LICENSE-2.0[m
[32m+[m[32m *[m
[32m+[m[32m * Unless required by applicable law or agreed to in writing, software[m
[32m+[m[32m * distributed under the License is distributed on an "AS IS" BASIS,[m
[32m+[m[32m * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.[m
[32m+[m[32m * See the License for the specific language governing permissions and[m
[32m+[m[32m * limitations under the License.[m
[32m+[m[32m */[m
[32m+[m[32m/*[m
[32m+[m[32m * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license[m
[32m+[m[32m * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template[m
[32m+[m[32m */[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m *[m
[32m+[m[32m * @author Hamza[m
[32m+[m[32m */[m
[32m+[m
[32m+[m[32mimport java.nio.charset.StandardCharsets;[m
[32m+[m[32mimport java.util.Base64;[m
[32m+[m[32mimport java.util.List;[m
[32m+[m
[32m+[m[32mimport javax.servlet.http.HttpServletRequest;[m
[32m+[m
[32m+[m[32mimport org.apache.logging.log4j.LogManager;[m
[32m+[m[32mimport org.apache.logging.log4j.Logger;[m
[32m+[m[32mimport org.springframework.beans.factory.annotation.Autowired;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.PathVariable;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.RequestMapping;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.RestController;[m
[32m+[m[32mimport org.springframework.web.server.ResponseStatusException;[m
[32m+[m[32mimport org.springframework.web.bind.annotation.RequestParam;[m
[32m+[m[32mimport org.springframework.http.HttpStatus;[m
[32m+[m
[32m+[m
[32m+[m[32mimport io.swagger.v3.oas.annotations.Operation;[m
[32m+[m[32mimport io.swagger.v3.oas.annotations.Parameter;[m
[32m+[m[32mimport org.solent.spring.map.user.dao.impl.UserRepository;[m
[32m+[m[32mimport org.solent.spring.map.user.model.dto.User;[m
[32m+[m[32mimport org.solent.spring.map.user.model.dto.UserRole;[m
[32m+[m
[32m+[m[32mimport org.springframework.stereotype.Component;[m
[32m+[m
[32m+[m[32m/**[m
[32m+[m[32m * this customises the OpenApi spec to include basic authorisation[m
[32m+[m[32m */[m
[32m+[m[32m@Component[m
[32m+[m[32mpublic class UserAuthoriserService {[m
[32m+[m
[32m+[m	[32mfinal static Logger LOG = LogManager.getLogger(UserAuthoriserService.class);[m
[32m+[m
[32m+[m	[32m@Autowired[m
[32m+[m	[32mUserRepository userRepository;[m
[32m+[m
[32m+[m	[32mpublic UserRole authorisedUserRole(HttpServletRequest httpRequest) {[m
[32m+[m
[32m+[m		[32mUserRole userRole = UserRole.ANONYMOUS;[m
[32m+[m
[32m+[m		[32mtry {[m
[32m+[m
[32m+[m			[32mfinal String authorization = httpRequest.getHeader("Authorization");[m
[32m+[m			[32mif (authorization != null && authorization.toLowerCase().startsWith("basic")) {[m
[32m+[m				[32m// Authorization: Basic base64credentials[m
[32m+[m				[32mString base64Credentials = authorization.substring("Basic".length()).trim();[m
[32m+[m				[32mbyte[] credDecoded = Base64.getDecoder().decode(base64Credentials);[m
[32m+[m				[32mString credentials = new String(credDecoded, StandardCharsets.UTF_8);[m
[32m+[m				[32m// credentials = username:password[m
[32m+[m				[32mfinal String[] values = credentials.split(":", 2);[m
[32m+[m				[32mString username = values[0];[m
[32m+[m				[32mString password = values[1];[m
[32m+[m[41m				[m
[32m+[m				[32mList<User> users = userRepository.findByUsername(username);[m
[32m+[m				[32mif (users.isEmpty() || ! username.equals(users.get(0).getUsername())) {[m
[32m+[m					[32mthrow new RuntimeException("unknown authenticated username "+username);[m
[32m+[m				[32m}[m
[32m+[m				[32mif (! users.get(0).isValidPassword(password) ) throw new RuntimeException("incorrect password for username "+username) ;[m
[32m+[m				[32mreturn users.get(0).getUserRole();[m
[32m+[m			[32m}[m
[32m+[m
[32m+[m		[32m} catch (Exception e) {[m
[32m+[m			[32mthrow new RuntimeException("problem decoding credentials: ", e);[m
[32m+[m		[32m}[m
[32m+[m
[32m+[m		[32mreturn userRole;[m
[32m+[m	[32m}[m
[32m+[m
[32m+[m[32m}[m
\ No newline at end of file[m
[1mdiff --git a/src/main/java/org/solent/spring/map/controller/MapPointRestController.java b/src/main/java/org/solent/spring/map/controller/MapPointRestController.java[m
[1mdeleted file mode 100644[m
[1mindex 06e63f8..0000000[m
[1m--- a/src/main/java/org/solent/spring/map/controller/MapPointRestController.java[m
[1m+++ /dev/null[m
[36m@@ -1,52 +0,0 @@[m
[31m-/*[m
[31m- * Licensed under the Apache License, Version 2.0 (the "License");[m
[31m- * you may not use this file except in compliance with the License.[m
[31m- * You may obtain a copy of the License at[m
[31m- *[m
[31m- *     http://www.apache.org/licenses/LICENSE-2.0[m
[31m- *[m
[31m- * Unless required by applicable law or agreed to in writing, software[m
[31m- * distributed under the License is distributed on an "AS IS" BASIS,[m
[31m- * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.[m
[31m- * See the License for the specific language governing permissions and[m
[31m- * limitations under the License.[m
[31m- */[m
[31m-package org.solent.spring.map.controller;[m
[31m-[m
[31m-import java.util.Optional;[m
[31m-[m
[31m-import org.solent.spring.map.model.MapPoint;[m
[31m-import org.solent.spring.map.repository.MapPointRepository;[m
[31m-import org.springframework.beans.factory.annotation.Autowired;[m
[31m-import org.springframework.web.bind.annotation.PathVariable;[m
[31m-import org.springframework.web.bind.annotation.RequestMapping;[m
[31m-import org.springframework.web.bind.annotation.RestController;[m
[31m-[m
[31m-import io.swagger.v3.oas.annotations.Operation;[m
[31m-import io.swagger.v3.oas.annotations.Parameter;[m
[31m-[m
[31m-/**[m
[31m- * Created by pingwin on 25.10.16.[m
[31m- */[m
[31m-@RestController[m
[31m-public class MapPointRestController {[m
[31m-	[m
[31m-	[m
[31m-    @Autowired[m
[31m-    MapPointRepository mapPointRepository;[m
[31m-[m
[31m-    @Operation(summary = "Get a list of map points")[m
[31m-    @RequestMapping("/get")[m
[31m-    public Iterable<MapPoint> list() {[m
[31m-        return mapPointRepository.findAll();[m
[31m-    }[m
[31m-[m
[31m-    @Operation(summary = "Get a specific map point by id")[m
[31m-    @RequestMapping("/get/{id}")[m
[31m-    public MapPoint getById(@Parameter(description = "id if point to be retreived") @PathVariable(value = "id") long id){[m
[31m-        Optional<MapPoint> mpo = mapPointRepository.findById(id);[m
[31m-        return (mpo.isEmpty()) ? null : mpo.get();[m
[31m-    }[m
[31m-[m
[31m-[m
[31m-}[m
[1mdiff --git a/src/main/java/org/solent/spring/map/model/MapPoint.java b/src/main/java/org/solent/spring/map/model/MapPoint.java[m
[1mindex d25ace2..5f38910 100644[m
[1m--- a/src/main/java/org/solent/spring/map/model/MapPoint.java[m
[1m+++ b/src/main/java/org/solent/spring/map/model/MapPoint.java[m
[36m@@ -16,12 +16,16 @@[m [mpackage org.solent.spring.map.model;[m
 [m
 import javax.persistence.Entity;[m
 import javax.persistence.GeneratedValue;[m
[32m+[m[32mimport javax.persistence.GenerationType;[m
 import javax.persistence.Id;[m
[32m+[m[32mimport javax.persistence.Lob;[m
[32m+[m[32mimport javax.persistence.Transient;[m
 [m
 @Entity[m
 public class MapPoint {[m
[32m+[m[41m   [m
     @Id[m
[31m-    @GeneratedValue[m
[32m+[m[32m    @GeneratedValue(strategy = GenerationType.IDENTITY)[m
     private Long id;[m
     private String name;[m
     private String description;[m
[36m@@ -29,6 +33,8 @@[m [mpublic class MapPoint {[m
     private double lat;[m
     private double lng;[m
 [m
[32m+[m[32m    @Lob[m
[32m+[m[32m    private byte[] image;[m
 [m
     @SuppressWarnings("unused")[m
     public MapPoint()[m
[36m@@ -42,7 +48,6 @@[m [mpublic class MapPoint {[m
         this.lat = lat;[m
         this.lng = lng;[m
     }[m
[31m-[m
     public Long getId() {[m
         return id;[m
     }[m
[36m@@ -89,7 +94,15 @@[m [mpublic class MapPoint {[m
     public void setLng(double lng) {[m
         this.lng = lng;[m
     }[m
[32m+[m[41m    [m
[32m+[m[32m     public byte[] getImage() {[m
[32m+[m[32m        return image;[m
[32m+[m[32m    }[m
 [m
[32m+[m[32m    public void setImage(byte[] image) {[m
[32m+[m[32m        this.image = image;[m
[32m+[m[32m    }[m
[32m+[m[41m    [m
 [m
     @Override[m
     public String toString() {[m
[36m@@ -102,4 +115,5 @@[m [mpublic class MapPoint {[m
                 ", lng=" + lng +[m
                 '}';[m
     }[m
[32m+[m
 }[m
[1mdiff --git a/src/main/java/org/solent/spring/map/repository/MapPointRepository.java b/src/main/java/org/solent/spring/map/repository/MapPointRepository.java[m
[1mindex a50e9b9..1481352 100644[m
[1m--- a/src/main/java/org/solent/spring/map/repository/MapPointRepository.java[m
[1m+++ b/src/main/java/org/solent/spring/map/repository/MapPointRepository.java[m
[36m@@ -13,11 +13,21 @@[m
  */[m
 package org.solent.spring.map.repository;[m
 [m
[32m+[m[32mimport java.util.List;[m
[32m+[m[32mimport java.util.Optional;[m
 import org.solent.spring.map.model.MapPoint;[m
 import org.springframework.data.jpa.repository.JpaRepository;[m
[32m+[m[32mimport org.springframework.data.jpa.repository.Query;[m
[32m+[m[32mimport org.springframework.data.repository.query.Param;[m
 import org.springframework.stereotype.Repository;[m
 [m
[32m+[m
 @Repository[m
[31m-public interface MapPointRepository extends JpaRepository<MapPoint,Long>{[m
[32m+[m[32mpublic interface MapPointRepository extends JpaRepository<MapPoint, Long> {[m
[32m+[m
[32m+[m[32m    @Query("select m from MapPoint m where m.name = :name")[m
[32m+[m[32m    List<MapPoint> findByName(@Param("name") String name);[m
 [m
[32m+[m[32m    @Query("select m from MapPoint m where m.id = :id")[m
[32m+[m[32m    Optional<MapPoint> findById(@Param("id") long id);[m
 }[m
[1mdiff --git a/src/main/java/org/solent/spring/map/controller/PageController.java b/src/main/java/org/solent/spring/map/repository/MapRepositoryConfig.java[m
[1msimilarity index 53%[m
[1mrename from src/main/java/org/solent/spring/map/controller/PageController.java[m
[1mrename to src/main/java/org/solent/spring/map/repository/MapRepositoryConfig.java[m
[1mindex 05b8f20..a955063 100644[m
[1m--- a/src/main/java/org/solent/spring/map/controller/PageController.java[m
[1m+++ b/src/main/java/org/solent/spring/map/repository/MapRepositoryConfig.java[m
[36m@@ -11,20 +11,17 @@[m
  * See the License for the specific language governing permissions and[m
  * limitations under the License.[m
  */[m
[31m-package org.solent.spring.map.controller;[m
[31m-[m
[31m-import org.springframework.stereotype.Controller;[m
[31m-import org.springframework.web.bind.annotation.RequestMapping;[m
[31m-[m
[31m-/**[m
[31m- * Created by pingwin on 27.10.16.[m
[32m+[m[32m/*[m
[32m+[m[32m * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license[m
[32m+[m[32m * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template[m
  */[m
[31m-@Controller[m
[31m-public class PageController {[m
[32m+[m[32mpackage org.solent.spring.map.repository;[m
 [m
[31m-    @RequestMapping("/")[m
[31m-    public String homePage(){[m
[31m-        return "LeafletJsp";[m
[31m-    }[m
[32m+[m[32mimport org.springframework.context.annotation.Configuration;[m
[32m+[m[32mimport org.springframework.data.jpa.repository.config.EnableJpaRepositories;[m
 [m
[32m+[m[32m@Configuration[m
[32m+[m[32m@EnableJpaRepositories(basePackages = "org.solent.spring.map.repository")[m
[32m+[m[32mpublic class MapRepositoryConfig {[m
[32m+[m[41m    [m
 }[m
[1mdiff --git a/src/main/java/org/solent/spring/map/user/dao/impl/PersistenceJPAConfig.java b/src/main/java/org/solent/spring/map/user/dao/impl/PersistenceJPAConfig.java[m
[1mindex 7440ca9..33591ae 100644[m
[1m--- a/src/main/java/org/solent/spring/map/user/dao/impl/PersistenceJPAConfig.java[m
[1m+++ b/src/main/java/org/solent/spring/map/user/dao/impl/PersistenceJPAConfig.java[m
[36m@@ -33,7 +33,7 @@[m [mimport org.springframework.data.jpa.repository.config.EnableJpaRepositories;[m
 [m
 @Configuration[m
 @EnableTransactionManagement[m
[31m-@EnableJpaRepositories(basePackages = "org.solent.com619.devops.user.dao.impl")[m
[32m+[m[32m@EnableJpaRepositories(basePackages = "org.solent.spring.map.user.dao.impl")[m
 // @PropertySource("classpath:persistence-test.properties") // set in calling configuration[m
 public class PersistenceJPAConfig {[m
 [m
[36m@@ -45,7 +45,7 @@[m [mpublic class PersistenceJPAConfig {[m
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() {[m
         final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();[m
         em.setDataSource(dataSource());[m
[31m-        em.setPackagesToScan("org.solent.com619.devops.user.model.dto" );[m
[32m+[m[32m        em.setPackagesToScan("org.solent.spring.map.user.model.dto","org.solent.spring.map.model");[m
 [m
         final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();[m
         em.setJpaVendorAdapter(vendorAdapter);[m
[1mdiff --git a/src/main/java/org/solent/spring/map/user/spring/service/PopulateDatabaseOnStart.java b/src/main/java/org/solent/spring/map/user/spring/service/PopulateDatabaseOnStart.java[m
[1mdeleted file mode 100644[m
[1mindex 22a071e..0000000[m
[1m--- a/src/main/java/org/solent/spring/map/user/spring/service/PopulateDatabaseOnStart.java[m
[1m+++ /dev/null[m
[36m@@ -1,84 +0,0 @@[m
[31m-/*[m
[31m- * Licensed under the Apache License, Version 2.0 (the "License");[m
[31m- * you may not use this file except in compliance with the License.[m
[31m- * You may obtain a copy of the License at[m
[31m- *[m
[31m- *     http://www.apache.org/licenses/LICENSE-2.0[m
[31m- *[m
[31m- * Unless required by applicable law or agreed to in writing, software[m
[31m- * distributed under the License is distributed on an "AS IS" BASIS,[m
[31m- * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.[m
[31m- * See the License for the specific language governing permissions and[m
[31m- * limitations under the License.[m
[31m- */[m
[31m-/*[m
[31m- * To change this license header, choose License Headers in Project Properties.[m
[31m- * To change this template file, choose Tools | Templates[m
[31m- * and open the template in the editor.[m
[31m- */[m
[31m-package org.solent.spring.map.user.spring.service;[m
[31m-[m
[31m-import java.util.List;[m
[31m-import javax.annotation.PostConstruct;[m
[31m-import org.apache.logging.log4j.LogManager;[m
[31m-import org.apache.logging.log4j.Logger;[m
[31m-import org.solent.spring.map.user.dao.impl.UserRepository;[m
[31m-import org.solent.spring.map.user.model.dto.User;[m
[31m-import org.solent.spring.map.user.model.dto.UserRole;[m
[31m-import org.springframework.beans.factory.annotation.Autowired;[m
[31m-import org.springframework.stereotype.Component;[m
[31m-[m
[31m-/**[m
[31m- *[m
[31m- * @author cgallen[m
[31m- */[m
[31m-@Component[m
[31m-public class PopulateDatabaseOnStart {[m
[31m-[m
[31m-    private static final Logger LOG = LogManager.getLogger(PopulateDatabaseOnStart.class);[m
[31m-[m
[31m-    private static final String DEFAULT_ADMIN_USERNAME = "globaladmin";[m
[31m-    private static final String DEFAULT_ADMIN_PASSWORD = "globaladmin";[m
[31m-[m
[31m-    private static final String DEFAULT_USER_PASSWORD = "user1234";[m
[31m-    private static final String DEFAULT_USER_USERNAME = "user1234";[m
[31m-[m
[31m-    @Autowired[m
[31m-    private UserRepository userRepository;[m
[31m-[m
[31m-    @PostConstruct[m
[31m-    public void initDatabase() {[m
[31m-        LOG.debug("initialising database with startup values");[m
[31m-[m
[31m-        // initialising admin and normal user if dont exist[m
[31m-        User adminUser = new User();[m
[31m-        adminUser.setUsername(DEFAULT_ADMIN_USERNAME);[m
[31m-        adminUser.setFirstName("default administrator");[m
[31m-        adminUser.setPassword(DEFAULT_ADMIN_PASSWORD);[m
[31m-        adminUser.setUserRole(UserRole.ADMINISTRATOR);[m
[31m-[m
[31m-        List<User> users = userRepository.findByUsername(DEFAULT_ADMIN_USERNAME);[m
[31m-        if (users.isEmpty()) {[m
[31m-            userRepository.save(adminUser);[m
[31m-            LOG.info("creating new default admin user:" + adminU