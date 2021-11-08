package com.grupo7.brflixapp.ui.fragments.profile.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.grupo7.brflixapp.R
import com.grupo7.brflixapp.databinding.FragmentProfileBinding
import com.grupo7.brflixapp.ui.activity.main.MainActivity
import com.grupo7.brflixapp.util.constants.Constants.Login.UserID
import com.grupo7.brflixapp.util.constants.Constants.Logout.LOGIN_TYPE
import com.squareup.picasso.Picasso

const val GET_IMAGE_REQUEST = 1

class profileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonLogout?.setOnClickListener{
            if(LOGIN_TYPE == 10) {
                Firebase.auth.signOut()
                startActivity(Intent(activity, MainActivity::class.java))
            } else if (LOGIN_TYPE == 20){
                Firebase.auth.signOut()
                logoutGoogle()
                startActivity(Intent(activity, MainActivity::class.java))
            }
        }

        binding?.changePictureProfile?.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, GET_IMAGE_REQUEST)
        }

        loadProfileImageFromStorage()



        binding?.cvProfileItemContainer?.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_passwordResetFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onActivityResult(request: Int, code: Int, intent: Intent?) {
        super.onActivityResult(request, code, intent)
        if (code == AppCompatActivity.RESULT_OK && request == GET_IMAGE_REQUEST && intent?.data != null) {
            val imageURI = intent.data!!

            imageURI.run {
                binding?.pictureCardProfile?.setImageURI(this)
                val firebase = FirebaseStorage.getInstance()
                val storage = firebase.getReference("UserProfileImages")
                val fileReference = storage.child("${UserID}.jpeg")
                fileReference.putFile(this)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Upload com sucesso!", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Upload falhou!", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun loadProfileImageFromStorage() {
        val firebase = FirebaseStorage.getInstance()
        val storage = firebase.getReference("UserProfileImages")
        storage.child("${UserID}.jpeg").downloadUrl.addOnSuccessListener {
            Picasso.get()
                .load(it.toString())
                .error(R.drawable.nophoto)
                .into(binding?.pictureCardProfile)

        }
    }

    private fun logoutGoogle() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(this.requireActivity(), gso).signOut()


    }


}