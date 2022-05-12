<template>
  <nav>
    <v-toolbar flat app>
      <v-toolbar-side-icon @click="drawer = !drawer" class="grey--text">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-toolbar-side-icon>
      <v-toolbar-title class="text black--text">
        <span class="font-weight-light">Admin</span>
        <span class="font-bolt"> ESX</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn flat color="grey" @click="$router.push({ name: 'loginAdmin' })">
        <span>Sign Out</span>
        <v-icon right>mdi-export</v-icon>
      </v-btn>
    </v-toolbar>

    <v-navigation-drawer app v-model="drawer" class="grey">
      <v-list-item-group
        v-model="group"
        active-class="deep-purple--text text--accent-5"
      >
        <v-list-item
          v-for="link in links"
          :key="link.text"
          router
          :to="link.route"
        >
          <v-list-tile-action>
            <v-icon class="black--text">{{ link.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title class="white--text">{{
              link.text
            }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-item>
        <!-- <v-list-item>
          <v-list-item-title>Foo</v-list-item-title>
        </v-list-item> -->
      </v-list-item-group>
    </v-navigation-drawer>

    <v-row>
      <v-col lg="8">
        <v-form ref="form" class="ml-6 mr-6">
          <div id="app">
            <v-app>
              <v-list subheader two-line>
                <v-subheader inset>Gyms</v-subheader>

                <v-list-item v-for="gym in gyms" :key="gym.id">
                  <v-list-item-avatar>
                    <v-icon class="grey lighten-1" dark> mdi-folder </v-icon>
                  </v-list-item-avatar>

                  <v-list-item-content>
                    <v-list-item-title v-text="gym.name"></v-list-item-title>

                    <v-list-item-subtitle
                      v-text="gym.adress"
                    ></v-list-item-subtitle>
                  </v-list-item-content>

                  <v-list-item-action>
                    <v-btn icon @click="update(gym.id)">
                      <v-icon color="grey lighten-1">mdi-pencil</v-icon>
                    </v-btn>
                    <v-btn icon @click="dialog = true">
                      <v-icon color="grey lighten-1">mdi-delete</v-icon>
                    </v-btn>
                    <v-dialog v-model="dialog" max-width="290">
                      <v-card>
                        <v-card-title class="text-h5"> Delete </v-card-title>

                        <v-card-text> Sigur vrei sa stergi Sala? </v-card-text>

                        <v-card-actions>
                          <v-spacer></v-spacer>

                          <v-btn
                            color="green darken-1"
                            text
                            @click="dialog = false"
                          >
                            Disagree
                          </v-btn>

                          <v-btn
                            color="green darken-1"
                            text
                            @click="deletee(gym.id)"
                          >
                            Agree
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-list-item-action>
                </v-list-item>
              </v-list>
            </v-app>
          </div>
        </v-form>
      </v-col>
      <v-col>
        <v-btn
          color="green darken-1"
          @click="$router.push({ name: 'addGym' })"
        >
          Add Gym
        </v-btn>
      </v-col>
    </v-row>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "GymsAdmin",
  data() {
    return {
      gyms: [],
    //   lodUserId: window.localStorage.getItem("idAdmin"),
      dialog: false,
      drawer: false,
      links: [
        { icon: "mdi-home", text: "Home", route: "/homeAdmin" },
        { icon: "mdi-account-multiple", text: "Users", route: "/admin/users" },
        {
          icon: "mdi-clipboard-text",
          text: "Subscriptions",
          route: "/admin/subscriptions",
        },
        {
          icon: "mdi-widgets",
          text: "Gyms",
          route: "/admin/gyms",
        },
      ],
    };
  },

  async created() {
    this.getGyms();
  },
  methods: {
    async update(id) {
    //   window.localStorage.setItem("back", "users");
      window.localStorage.setItem("idGym", id);
      this.$router.push({
        name: "gymsAdminUpdate",
        params: { idGym: id },
      });
    },
    async deletee(id) {
      this.dialog = false;
      try {
        await axios.delete("http://localhost:8081/sala/" + id);
        this.getGyms();
      } catch (e) {
        alert("Ceva gresit!");
      }
    },
    async getGyms() {
      try {
        //   alert("ceva");
        var result = await axios.get("http://localhost:8081/sala/");
        this.gyms = result.data;
      } catch (e) {
        alert("Error Gyms show!");
      }
    },
  },
};
</script>


