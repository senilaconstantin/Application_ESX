
<template>
  <nav>
    <v-toolbar flat app class="blue">
      <v-toolbar-side-icon @click="drawer = !drawer" class="grey--text">
        <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-toolbar-side-icon>
      <v-toolbar-title class="text black--text">
        <span class="font-weight-light">User</span>
        <span class="font-bolt"> ESX</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn flat color="grey" @click="$router.push({ name: 'login' })">
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

                    <!-- <v-list-item-title
                      v-text="user.lastName"
                    ></v-list-item-title> -->

                    <v-list-item-subtitle
                      v-text="gym.adress"
                    ></v-list-item-subtitle>
                  </v-list-item-content>

                  <v-item-action>
                    <v-btn icon @click="addRD(gym.id)">
                      <v-icon color="grey lighten-1">mdi-star</v-icon>
                    </v-btn>
                    <v-btn icon @click="reviewView(gym.id)">
                      <v-icon color="grey lighten-1">mdi-format-list-text</v-icon>
                    </v-btn>
                    <v-dialog v-model="dialog" max-width="290">
                      <v-card class="elevation-16 mx-auto" width="300">
                        <v-card-title class="headline" primary-title>
                          Rate the Gym!
                        </v-card-title>
                        <v-card-text>
                          <!--         If you enjoy using Vuetify, please take a few seconds to rate your experience with the framework. It really helps! -->
                          <v-text-field
                            v-model="comment"
                            label="Comment"
                            required
                          ></v-text-field>
                          <div class="text-xs-center mt-5">
                            <v-rating
                              v-model="rating"
                              color="yellow darken-3"
                              background-color="grey darken-1"
                              empty-icon="$vuetify.icons.ratingFull"
                              half-increments
                              hover
                            ></v-rating>
                          </div>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions class="justify-space-between">
                          <v-btn flat @click="dialog = false">No Thanks</v-btn>
                          <v-btn
                            color="primary"
                            flat
                            @click="addReview(gm)"
                          >
                            Rate Now
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-item-action>
                </v-list-item>
              </v-list>
            </v-app>
          </div>
        </v-form>
      </v-col>
    </v-row>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "ViewGymsView",
  data() {
    return {
      subscriptions: "",
      idSubs: "",
      comment: "",
      gm:"",
      rating: 0,
      gyms: [],
      dialog: false,
      drawer: false,
      links: [
        { icon: "mdi-home", text: "Home", route: "/home" },
        // { icon: "mdi-account-multiple", text: "Users", route: "/home" },
        {
          icon: "mdi-clipboard-text",
          text: "Subscriptions",
          route: "/home/subscriptions",
        },
        {
          icon: "mdi-widgets",
          text: "Gyms",
          route: "/home/gyms",
        },
      ],
      users: [],
      lodUserId: window.localStorage.getItem("idUser"),
    };
  },

  async created() {
    try {
      var result = await axios.get(
        "http://localhost:8081/users/getAb/" + this.lodUserId
      );
      this.subscriptions = result.data;
      this.idSubs = this.subscriptions.id;

      var resultGym = await axios.get(
        "http://localhost:8081/abonament/getSala/" + this.idSubs
      );
      this.gyms = resultGym.data;
    } catch (e) {
      alert("Nu veti inca un abonament activ!");
      this.$router.push({ name: "home" });
    }
  },

  methods: {
    async addRD(id){
      this.dialog = true;
      this.gm=id;

    },
    async addReview(idGym) {
      this.dialog = false;
      // alert(idGym);
      try {
        await axios.post("http://localhost:8081/rating", {
          note: this.rating,
          comment: this.comment,
          idUser: this.lodUserId,
          idGym: idGym,
        });
        // this.$router.push({ name: "users" });
      } catch (e) {
        alert(this.rating + " " + this.comment);
      }
    },
    async reviewView(idGym){
       this.$router.push({ name: 'reviewsU' }),
       window.localStorage.setItem("idGymU", idGym);
    },
  },
};
</script>

<style lang="scss" scoped>
input {
  border: 2px solid black;
}
</style>


