<template>
  <v-row>
    <v-col lg="8">
      <v-form ref="form" class="ml-6 mr-6">
        <div id="app">
          <v-app id="inspire">
            <div>
              <!-- <v-expansion-panels v-model="panel" :disabled="disabled" multiple>
                <v-expansion-panel>
                  <v-panel-header>First Name</v-panel-header>
                    <h1>{{ user.firstName }}</h1>
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-panel-header>Last Name</v-panel-header>
                    <h1>{{ user.lastName }}</h1>
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-panel-header>Email</v-panel-header>                  
                    <h1>{{ user.mail }}</h1>                
                </v-expansion-panel>

                <v-expansion-panel>
                  <v-panel-header>Adress</v-panel-header>
                    <h1>{{ user.adress }}</h1>
                </v-expansion-panel>
              </v-expansion-panels> -->

              <v-btn
                color="success"
                class="mr-4"
                @click="update(lodUserId)"
              >
                Update
              </v-btn>
              <v-btn
                color="success"
                class="mr-5"
                @click="$router.push({ name: 'delete' })"
              >
                Delete
              </v-btn>
            </div>
            <v-list subheader two-line>
              <v-subheader inset>Users</v-subheader>

              <v-list-item v-for="user in users" :key="user.id">
                <v-list-item-avatar>
                  <v-icon class="grey lighten-1" dark> mdi-folder </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title
                    v-text="user.firstName"
                  ></v-list-item-title>

                  <v-list-item-subtitle
                    v-text="user.mail"
                  ></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn
                    icon
                    @click="
                      $router.push({ name: 'update', params: { id: user.id } })
                    "
                  >
                    <v-icon color="grey lighten-1">mdi-pencil</v-icon>
                  </v-btn>
                  <v-btn icon @click="
                      deletee(user.id)
                    ">
                    <v-icon color="grey lighten-1">mdi-delete</v-icon>
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-app>
        </div>
      </v-form>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";

export default {
  name: "HomeView",
  data() {
    return {
      users: [],
      lodUserId: window.localStorage.getItem("id"),
    };
  },

  async created() {
    this.getUsers();
  },
   methods: {
    async deletee(id) {
      try {
        await axios.delete("http://localhost:8081/users/"+id)
        this.getUsers();
      } catch (e) {
        alert("Ceva gresit!");
      }
    },
    async getUsers(){
      var result = await axios.get("http://localhost:8081/users/");
      this.users = result.data;
    },
    async update(id) {
      window.localStorage.setItem("back", "home");
      this.$router.push({
        name: "update",
        params: { id: id },
      });
    },
  },
};
</script>


